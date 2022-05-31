package co.com.techincalTest.usecase.mutant;

import co.com.techincalTest.model.*;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.regex.Pattern;


@RequiredArgsConstructor
public class MutantUseCase {

    private static final Logger logger = LogManager.getLogger(MutantUseCase.class);
    private boolean mutant;
    private static final Pattern DNA_BASE_PATTERN = Pattern.compile("[atcg]+", Pattern.CASE_INSENSITIVE);

    private final HorizontalUseCase horizontalSequenceUseCase;
    private final VerticalUseCase verticalUseCase;

    private final DiagonalUseCase diagonalUseCase;

    private final DiagonalUpUseCase diagonalUpUseCase;
    /**
     * Procesa y retorna si el dna de una secuencia es mutante o humano
     * @param dna
     *@return verdadero si la secuencia del dna es mutante, falso si es humano
     */
    public boolean isMutantDNA(SequenceDna dna) throws DNAStructureLengthException, InvalidCaractersDNAException {
        boolean isMutant = validateSizeSequenceDNA(dna);
        Mutant dnaMutant = Mutant.builder()
                .dna(dna.getDna())
                .isMutant(isMutant)
                .build();
        return isMutant;
    }





    private boolean validateSizeSequenceDNA(SequenceDna dna) throws DNAStructureLengthException, InvalidCaractersDNAException {
            if(dna.getDna().size() <= Constantes.DEFAULT_SEQUENCE_SIZE_MUTANT){
                    logger.error("La longitud mínima debe ser mayor que {} para pertenecer al mutante.",
                            Constantes.DEFAULT_SEQUENCE_SIZE_MUTANT);
                    return false;
            }
            char[][] dnaSqe = this.processDNASequence(dna);
            PosicionesMutante detectorMutante = PosicionesMutante.builder()
                    .dna(dnaSqe)
                    .countSequencesMatchMutant(Constantes.COUNT_SEQUENCE_MATCH_MUTANT)
                    .sequenceToMudant(Constantes.DEFAULT_SEQUENCE_SIZE_MUTANT)
                    .build();
            return isMutante(detectorMutante);
    }

    private char[][] processDNASequence(SequenceDna dnaSequence) throws DNAStructureLengthException, InvalidCaractersDNAException {
        int vectorLength = dnaSequence.getDna().size();
        char[][] dna = new char[vectorLength][vectorLength];
        for (int i = 0; i < vectorLength; i++) {
            String dnaRow = dnaSequence.getDna().get(i);
            validateDNASequenceEstructureMutant(vectorLength, dnaRow);
            dna[i] = dnaRow.toUpperCase().toCharArray();
        }
        return dna;
    }

    private void validateDNASequenceEstructureMutant(int vectorLength, String dnaRow) throws DNAStructureLengthException,
            InvalidCaractersDNAException {
        if (dnaRow.length() != vectorLength) {
            String message = "The length of the DNA sequences must be the same size. Expected " +  vectorLength + ", found " + dnaRow.length() + " " + dnaRow;
            throw new DNAStructureLengthException(message);
        } else if (!DNA_BASE_PATTERN.matcher(dnaRow).matches()) {
            String message = "Los únicos caracteres válidos son A, T, C, G. Encontrado " + dnaRow;
            throw new InvalidCaractersDNAException(message);
        }
    }

    /*
    * @return verdadero if encuentra un mutante
    * */
    private boolean isMutante(PosicionesMutante identificadorMutante) {
        boolean horizontal = horizontalSequenceUseCase.searchMutanteSequences(identificadorMutante);
        boolean vertical = verticalUseCase.searchMutanteSequencesVertical(identificadorMutante);
        boolean diagonal = diagonalUseCase.searchMutanteSequencesDiagonal(identificadorMutante);
        boolean diagonalUp = diagonalUpUseCase.searchMutanteSequencesDiagonalUp(identificadorMutante);
        if(horizontal || vertical || diagonal || diagonalUp){
                return true;
        }else{
            return false;
        }

    }

}
