package co.com.techincalTest.usecase.mutant;

import co.com.techincalTest.model.Coordinate;
import co.com.techincalTest.model.Direction;
import co.com.techincalTest.model.PosicionesMutante;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class HorizontalUseCase {

    private static final Logger log = LogManager.getLogger(HorizontalUseCase.class);
    private static final Direction DIRECTION = Direction.HORIZONTAL;

    public boolean searchMutanteSequences(PosicionesMutante posicionesMutante){
        log.debug("Analyze colums of given dna sequence at direction {}: ", DIRECTION);
        char[][] dna = posicionesMutante.getDna();
        for (int row = 0; row < dna.length; row++) {
            boolean match = findMutantSequence(Coordinate.at(dna, row, 0), posicionesMutante);
            if (match) {
                return true;
            }
        }
        return false;
    }


    private boolean findMutantSequence(Coordinate coordidate, PosicionesMutante context) {
        char currentChar = coordidate.dna[coordidate.row][coordidate.column];
        int sequence = 1;
        boolean match = false;
        while (hasNext(coordidate)) {
            moveNext(coordidate);

            if (currentChar != coordidate.curruntChar) {
                sequence = 1;
                currentChar = coordidate.curruntChar;
            } else if (++sequence >= context.getSequenceToMudant()) {
                log.debug("New mutant sequences end at: {}", coordidate);
                context = newSequenceMatch(context);

                if (hasMatchSequencesMutant(context)) {
                    match = true;
                }
            }
        }
        return match;
    }


    private boolean hasNext(Coordinate coordinate) {
        return coordinate.column + 1 <= coordinate.safeIndex;
    }

    private void moveNext(Coordinate coordinate) {
        coordinate.column++;
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
