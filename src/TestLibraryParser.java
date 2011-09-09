//Copyright (c) 2011 Juan Cortez
//
//Permission is hereby granted, free of charge, to any person obtaining a copy
//of this software and associated documentation files (the "Software"), to deal
//in the Software without restriction, including without limitation the rights 
//to use, copy, modify, merge, publish, distribute, sublicense, and/or sell 
//copies of the Software, and to permit persons to whom the Software is furnished
//to do so, subject to the following conditions:
//
//The above copyright notice and this permission notice shall be included in all 
//copies or substantial portions of the Software.
//
//THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR 
//IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
//FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR 
//COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER 
//IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN 
//CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

import static org.junit.Assert.*;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;

/**
 *Tests a {@link RhythmboxXMLLibraryParser} using a condensed and modified 
 *version of my own Rhythmbox XML library.
 */
public class TestLibraryParser 
{
	//The parser that will be used for parsing my Rhythmbox library.
	RhythmboxXMLLibraryParser parser;

	/**
	 * The initialization of a parser that will be used for the tests.
	 * @throws IOException 
	 * @throws SAXException 
	 * @throws ParserConfigurationException 
	 */
	@Before
	public void before() throws ParserConfigurationException, SAXException, 
		IOException
	{
		parser =  new RhythmboxXMLLibraryParser("testXMLlibrary");
		parser.parseLibrary();
	}

	/**
	 * Tests that there are 16 songs that were parsed (because my condensed 
	 * library contains 16 songs) by looking at the number of {@link Song}
	 * entries in the array list of songs.
	 * @throws IOException 
	 * @throws SAXException 
	 * @throws ParserConfigurationException 
	 */
	@Test
	public void testNumOfSongs() throws ParserConfigurationException, 
		SAXException, IOException
	{
		assertEquals(16, parser.returnSongs().size());
	}

	/**
	 * The song "Hotel California" should be the fifteenth song that was parsed,
	 * so it is obtained from the fifteenth position in the array list of {@link 
	 * Song} objects, and its information is checked to make sure it was parsed 
	 * right.
	 */
	@Test
	public void testHotelCalifornia()
	{
		Song theSong = parser.returnSongs().get(14);
		assertEquals("Hotel California", theSong.getTitle());
		assertEquals("Eagles", theSong.getArtist());
		assertEquals("location", theSong.getLocation());
		assertEquals("Rock", theSong.getGenre());
		assertEquals(1, theSong.getTrackNumber());
		assertEquals(0, theSong.getRating());
		assertEquals(0, theSong.getDiscNumber());
		assertEquals(96, theSong.getBitRate());
		assertEquals("", theSong.getAlbumArtist());
		assertEquals("", theSong.getComments());
		assertEquals(0, theSong.getBPM());
	}

	/**
	 * Tests {@link RhythmboxXMLLibraryParser#searchForSongByTitle(String)} by 
	 * searching for every song in my own library.
	 * @throws SongNotFoundException 
	 * @throws IOException 
	 * @throws SAXException 
	 * @throws ParserConfigurationException 
	 */
	@Test
	public void searchForSongByTitleTest() throws SongNotFoundException, 
	ParserConfigurationException, SAXException, IOException 
	{
		parser.searchForSongByTitle("If I Needed Someone");
		parser.searchForSongByTitle("Wait");
		parser.searchForSongByTitle("I'm Looking Through You");
		parser.searchForSongByTitle("Michelle");
		parser.searchForSongByTitle("Girl");
		parser.searchForSongByTitle("Run For Your Life");
		parser.searchForSongByTitle("Nowhere Man");
		parser.searchForSongByTitle("The Word");
		parser.searchForSongByTitle("What Goes On");
		parser.searchForSongByTitle("In My Life");
		parser.searchForSongByTitle("Drive My Car");
		parser.searchForSongByTitle("You Won't See Me");
		parser.searchForSongByTitle("Norwegian Wood (This Bird Has Flown)");
		parser.searchForSongByTitle("Think For Yourself");
		parser.searchForSongByTitle("Hotel California");
		parser.searchForSongByTitle("Rope");
	}

	/**
	 * Another test for {@link RhythmboxXMLLibraryParser#searchForSongByTitle
	 * (String)}, but this one should fail because the song that will be 
	 * searched for is not in my library.
	 * @throws IOException 
	 * @throws SAXException 
	 * @throws ParserConfigurationException 
	 * @throws SongNotFoundException 
	 */
	@Test (expected = SongNotFoundException.class)
	public void searchForSongByTitleFailTest() throws 
		ParserConfigurationException, SAXException, IOException, 
		SongNotFoundException
	{
		parser.searchForSongByTitle("Bohemian Rhapsody");
	}
}
