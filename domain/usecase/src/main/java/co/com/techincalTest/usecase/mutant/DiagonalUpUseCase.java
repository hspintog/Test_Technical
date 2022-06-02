package co.com.techincalTest.usecase.mutant;

import co.com.techincalTest.model.Coordinate;
import co.com.techincalTest.model.Direction;
import co.com.techincalTest.model.PosicionesMutante;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DiagonalUpUseCase {

    private static final Logger log = LogManager.getLogger(DiagonalUpUseCase.class);
    private static final Direction DIRECTION = Direction.DIAGONAL_UP;

    public boolean searchMutanteSequencesDiagonalUp(PosicionesMutante context) {
        log.debug("Analyze colums of given dna sequence at direction {}: ", DIRECTION);
        char[][] dna = context.getDna();

        int fistIndexRow = dna.length - 1;
        int fistIndexColumn = 0;
        boolean match = findMutantSequence(Coordinate.at(dna, fistIndexRow, fistIndexColumn), context);
        if (match) {
            return true;
        }
        for (int row = 1; row <= dna.length - context.getSequenceToMudant(); row++) {
            match = findMutantSequence(Coordinate.at(dna, fistIndexRow - row, 0, row), context)
                    || findMutantSequence(Coordinate.at(dna, fistIndexRow, row, row), context);
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
        while (hasNext(coordidate, sequence, context)) {
            this.moveNext(coordidate);

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


    private boolean hasNext(Coordinate coordinate, int actualSequence, PosicionesMutante context) {
        int index = coordinate.subIndex;
        int available = coordinate.size - index;
        return available + actualSequence >= context.getSequenceToMudant() && coordinate.row - 1 >= 0;
    }

    private void moveNext(Coordinate coordinate) {
        coordinate.row--;
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
