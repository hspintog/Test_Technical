package co.com.techincalTest.usecase.mutant;

import co.com.techincalTest.model.Coordinate;
import co.com.techincalTest.model.Direction;
import co.com.techincalTest.model.PosicionesMutante;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class VerticalUseCase {
    private static final Logger log = LogManager.getLogger(VerticalUseCase.class);

    private static final Direction DIRECTION = Direction.VERTICAL;

    public boolean searchMutanteSequencesVertical(PosicionesMutante context) {
        log.debug("Analyze colums of given dna sequence at direction {}: ", DIRECTION);
        char[][] dna = context.getDna();
        for (int column = 0; column < dna.length; column++) {
            boolean match = findMutantSequence(Coordinate.at(dna, 0, column), context);
            if (match) {
                return true;
            }
        }
        return false;
    }


    private boolean findMutantSequence(Coordinate coordidate, PosicionesMutante posicionesMutante) {
        char currentChar = coordidate.dna[coordidate.row][coordidate.column];
        int sequence = 1;
        while (hasNext(coordidate)) {
            moveNext(coordidate);

            if (currentChar != coordidate.curruntChar) {
                sequence = 1;
                currentChar = coordidate.curruntChar;
            } else if (++sequence >= posicionesMutante.getSequenceToMudant()) {
                log.debug("New mutant sequences end at: {}", coordidate);
                posicionesMutante = newSequenceMatch(posicionesMutante);

                if (hasMatchSequencesMutant(posicionesMutante)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean hasNext(Coordinate coordinate) {
        return coordinate.row + 1 <= coordinate.safeIndex;
    }

    private void moveNext(Coordinate coordinate) {
        coordinate.row++;
        coordinate.subIndex++;
        coordinate.lastChar = coordinate.curruntChar;
        coordinate.curruntChar = coordinate.dna[coordinate.row][coordinate.column];
    }

    private PosicionesMutante newSequenceMatch(PosicionesMutante context) {
        context.setMatchs(context.getMatchs() + 1);
        return context;
    }

    private boolean hasMatchSequencesMutant(PosicionesMutante context) {
        log.debug("Analyze if has more than {} mutant sequences. Actutal: {} ",
                context.getCountSequencesMatchMutant() - 1, context.getMatchs());
        int count = context.getCountSequencesMatchMutant();
        return context.getMatchs() >= count;
    }
}
