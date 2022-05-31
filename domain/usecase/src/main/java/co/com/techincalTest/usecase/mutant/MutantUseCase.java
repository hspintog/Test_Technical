package co.com.techincalTest.usecase.mutant;

import co.com.techincalTest.model.*;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.regex.Pattern;


@RequiredArgsConstructor
public class MutantUseCase {

    private static final Logger logger = LogManager.getLogger(MutantUseCase.class);
    private int match;
    private static final Pattern DNA_BASE_PATTERN = Pattern.compile("[atcg]+", Pattern.CASE_INSENSITIVE);

    /**
     * Procesa y retorna si el dna de una secuencia es mutante o humano
     * @param dna
     *@return verdadero si la secuencia del dna es mutante, falso si es humano
     */
    public boolean isMutantDNA(SequenceDna dna) throws DNAStructureLengthException, InvalidCaractersDNAException {
        //SequenceDna sequence = SequenceDna.builder().dna(dna).build();
        boolean isMutant = validateSizeSequenceDNA(dna);
        Mutant dnaMutant = Mutant.builder()
                .dna(dna.getDna())
                .isMutant(isMutant)
                .build();
        return isMutant;
    }





    private boolean validateSizeSequenceDNA(SequenceDna dna) throws DNAStructureLengthException, InvalidCaractersDNAException {
            if(dna.getDna().length <= Constantes.DEFAULT_SEQUENCE_SIZE_MUTANT){
                    logger.error("La longitud mínima debe ser mayor que {} para pertenecer al mutante.",
                            Constantes.DEFAULT_SEQUENCE_SIZE_MUTANT);
                    return false;
            }
            char[][] dnaSqe = this.processDNASequence(dna);
            return validateSequence(dnaSqe);
    }

    private char[][] processDNASequence(SequenceDna dnaSequence) throws DNAStructureLengthException, InvalidCaractersDNAException {
        int vectorLength = dnaSequence.getDna().length;
        char[][] dna = new char[vectorLength][vectorLength];
        for (int i = 0; i < vectorLength; i++) {
            String dnaRow = dnaSequence.getDna()[i];
            validateDNASequenceEstructureMutant(vectorLength, dnaRow);
            dna[i] = dnaRow.toUpperCase().toCharArray();
        }
        return dna;
    }

    private void validateDNASequenceEstructureMutant(int vectorLength, String dnaRow) throws DNAStructureLengthException, InvalidCaractersDNAException {
        if (dnaRow.length() != vectorLength) {
            String message = "The length of the DNA sequences must be the same size. Expected " +  vectorLength + ", found " + dnaRow.length() + " " + dnaRow;
            throw new DNAStructureLengthException(message);
        } else if (!DNA_BASE_PATTERN.matcher(dnaRow).matches()) {
            String message = "Los únicos caracteres válidos son A, T, C, G. Encontrado " + dnaRow;
            throw new InvalidCaractersDNAException(message);
        }
    }


    private boolean validateSequence(char[][] dnaSeq){
        return  validateSequenceHorizontal(dnaSeq);
    }


    private boolean validateSequenceHorizontal(char[][] dna) {
        boolean validSeq = false;
        for (int row = 0; row < dna.length; row++) {
            boolean match = findMutantSequence(Coordinate.at(dna, row, 0));
            if (match) {
                validSeq = true;
                break;
            }
        }
        return validSeq;
    }


    private boolean findMutantSequence(Coordinate coordidate) {
        char currentChar = coordidate.dna[coordidate.row][coordidate.column];
        int sequence = 1;
        while (hasNext(coordidate, sequence)) {
            moveNext(coordidate);
            if (currentChar != coordidate.curruntChar) {
                sequence = 1;
                currentChar = coordidate.curruntChar;
            } else if (++sequence >= Constantes.DEFAULT_SEQUENCE_SIZE_MUTANT) {
                this.newSequenceMatch();
                if (hasMatchSequencesMutant()) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean hasNext(Coordinate coordinate, int actualSequence) {
        return coordinate.column + 1 <= coordinate.safeIndex;
    }


    private void moveNext(Coordinate coordinate) {
        coordinate.column++;
        coordinate.subIndex++;
        coordinate.lastChar = coordinate.curruntChar;
        coordinate.curruntChar = coordinate.dna[coordinate.row][coordinate.column];
    }

    private void newSequenceMatch() {
        this.match = this.match + 1;
    }


    private boolean hasMatchSequencesMutant() {
        int count = Constantes.COUNT_SEQUENCE_MATCH_MUTANT;
        return this.match >= count;
    }

}
