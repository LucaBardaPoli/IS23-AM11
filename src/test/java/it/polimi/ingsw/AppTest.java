package it.polimi.ingsw;

import it.polimi.ingsw.model.Position;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase
{
    Position p;
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
        p = new Position(1, 2);
    }

    /**
     * Rigourous Test :-)
     */
    public void testColumn()
    {
        assert( p.getColumn() == 32);
    }

    public void testRow()
    {
        assert( p.getRow() == 1);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }
}
