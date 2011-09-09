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

import java.io.IOException;
import java.util.ArrayList;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * The RhythmboxXMLLibraryParser class handles everything that is required to
 * parse a Rhythmbox XML library.
 */
public class RhythmboxXMLLibraryParser 
{
	//An array list of Song objects that will hold information about every
	//song in a Rhythmbox library.
	private ArrayList<Song> songLibrary;
	//The location of the user's Rhythmbox XML library.
	private String libLocation;
	//The modified SAX2 handler that will be used to parse the library.
	private RhythmboxLibraryHandler handler;

	public RhythmboxXMLLibraryParser()
	{
		//The default location of a Rhythmbox XML library is used to obtain
		//the Rhythmbox library that will be parsed.
		libLocation = System.getProperty("user.home") + 
				"/.local/share/rhythmbox/rhythmdb.xml";
		handler = new RhythmboxLibraryHandler();
		songLibrary = new ArrayList<Song>();
	}

	/**
	 * If a different location for the library is specified in the constructor,
	 * then it is used instead.
	 * @param loc the location of the user's Rhythmbox XML library
	 */
	public RhythmboxXMLLibraryParser(String loc)
	{
		libLocation = loc;
		handler = new RhythmboxLibraryHandler();
		songLibrary = new ArrayList<Song>();
	}

	/**
	 * Parses the user's Rhythmbox XML library so that it can be searched.
	 * @throws SAXException 
	 * @throws ParserConfigurationException 
	 * @throws IOException 
	 */
	public void parseLibrary() throws ParserConfigurationException, SAXException, 
		IOException
	{
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser parser = factory.newSAXParser();
		parser.parse(libLocation, handler);
	}

	/**
	 * Returns the array list of songs in a Rhythmbox XML library obtained by 
	 * parsing it.
	 * @return array list of songs.
	 */
	public ArrayList<Song> returnSongs()
	{
		return songLibrary;
	}

	/**
	 * Prints out all of the songs that were read from a Rhythmbox XML library 
	 * and their information by traversing the array list of songs. Useful 
	 * for debugging purposes.
	 */
	public void printLibrary()
	{
		for (int i = 0; i < songLibrary.size(); i++)
		{
			Song tempSong = songLibrary.get(i);
			System.out.println("Song title: " + tempSong.getTitle());
			System.out.println("Song artist: " + tempSong.getArtist());
			System.out.println("Song album: " + tempSong.getAlbum());
			System.out.println("Song location: " + tempSong.getLocation());
			System.out.println("Song genre: " + tempSong.getGenre());
			System.out.println("Song track number: " + tempSong.getTrackNumber());
			System.out.println("Song rating: " + tempSong.getRating());
			System.out.println("Song disc number: " + tempSong.getDiscNumber());
			System.out.println("Song bit rate: " + tempSong.getBitRate());
			System.out.println("Song album artist: " + tempSong.getAlbumArtist());
			System.out.println("Song comments: " + tempSong.getComments());
			System.out.println("Song BPM: " + tempSong.getBPM());
			System.out.println();
		}
	}

	/**
	 * Sequentially searches for a song in the user's Rhythmbox library and
	 * returns its location.
	 * @param title a song's title
	 * @return a song's location
	 */
	public String searchForSongByTitle(String title) throws SongNotFoundException
	{
		String locationToReturn = "";
		boolean songFound = false;

		for (int i = 0; i < songLibrary.size(); i++)
		{
			if (songLibrary.get(i).getTitle().equalsIgnoreCase(title))
			{
				locationToReturn = songLibrary.get(i).getLocation();
				songFound = true;
				break;
			}
		}

		//If the song is not found, then a SongNotFoundException is thrown.
		if (!songFound)
		{
			throw new SongNotFoundException();
		}

		return locationToReturn;
	}

	/**
	 * A SAX2 event handler for parsing a Rhythmbox XML library.
	 */
	private class RhythmboxLibraryHandler extends DefaultHandler
	{
		//Temporary variables that will be used to hold information about the
		//song that is currently read from a Rhythmbox XML library.
		private String tempSongTitle;
		private String tempSongArtist;
		private String tempSongAlbum;
		private String tempSongLocation;
		private String tempSongGenre;
		private int tempSongTrackNumber;
		private int tempSongRating;
		private int tempSongDiscNumber;
		private int tempSongBitRate;
		private String tempSongAlbumArtist;
		private String tempSongComments;
		private int tempSongBPM;
		//Booleans stating whether a particular element of a Rhythmbox XML 
		//library (its start tag) has been read.
		private boolean readSong;
		private boolean readSongTitle;
		private boolean readSongArtist;
		private boolean readSongAlbum;
		private boolean readSongLocation;
		private boolean readSongGenre;
		private boolean readSongTrackNumber;
		private boolean readSongRating;
		private boolean readSongDiscNumber;
		private boolean readSongBitRate;
		private boolean readSongAlbumArtist;
		private boolean readSongComments;
		private boolean readSongBPM;

		public RhythmboxLibraryHandler()
		{
			readSong = false;
			readSongTitle = false;
			readSongArtist = false;
			readSongAlbum = false;
			readSongLocation = false;
			readSongGenre = false;
			readSongTrackNumber = false;
			readSongRating = false;
			readSongDiscNumber = false;
			readSongBitRate = false;
			readSongAlbumArtist = false;
			readSongComments = false;
			readSongBPM = false;
			tempSongTitle = "";
			tempSongArtist = "";
			tempSongAlbum = "";
			tempSongLocation = "";
			tempSongGenre = "";
			tempSongTrackNumber = 0;
			tempSongRating = 0;
			tempSongDiscNumber = 0;
			tempSongBitRate = 0;
			tempSongAlbumArtist = "";
			tempSongComments = "";
			tempSongBPM = 0;
		}

		/**
		 * @see org.xml.sax.helpers.DefaultHandler#startElement(String uri, 
		 * String localName, String qName, Attributes attributes)
		 */
		public void startElement(String uri, String localName,String qName, 
				Attributes attributes) throws SAXException 
		{
			//Checks to see if the entry that was read is a song.
			if (qName.equals("entry") && attributes.getValue("type").equals("song"))
			{
				readSong = true;
			}

			//The song elements are only processed if an entry is a song in the
			//Rhythmbox XML library.
			if (readSong)
			{
				//Checks to see which element start tag from a song entry is read.
				if (qName.equals("title"))
				{
					readSongTitle = true;
				}
				else if (qName.equals("artist"))
				{
					readSongArtist = true;
				}
				else if (qName.equals("album"))
				{
					readSongAlbum = true;
				}
				else if (qName.equals("location"))
				{
					readSongLocation = true;
				}
				else if (qName.equals("genre"))
				{
					readSongGenre = true;
				}
				else if (qName.equals("track-number"))
				{
					readSongTrackNumber = true;
				}
				else if (qName.equals("rating"))
				{
					readSongRating = true;
				}
				else if (qName.equals("disc-number"))
				{
					readSongDiscNumber = true;
				}
				else if (qName.equals("bitrate"))
				{
					readSongBitRate = true;
				}
				else if (qName.equals("album-artist"))
				{
					readSongAlbumArtist = true;
				}
				else if (qName.equals("comment"))
				{
					readSongComments = true;
				}
				else if (qName.equals("beats-per-minute"))
				{
					readSongBPM = true;
				}
			}
		}

		/**
		 * @see org.xml.sax.helpers.DefaultHandler#characters(char[] ch, int
		 * start, int length)
		 */
		public void characters(char ch[], int start, int length) throws 
			SAXException
		{
			//The appropriate variables are set depending on what was read from
			//the XML file.
			if (readSongTitle)
			{
				tempSongTitle = new String(ch, start, length);
				readSongTitle = false;
			}
			else if (readSongArtist)
			{
				tempSongArtist = new String(ch, start, length);
				readSongArtist = false;
			}
			else if (readSongAlbum)
			{
				tempSongAlbum = new String(ch, start, length);
				readSongAlbum = false;
			}
			else if (readSongLocation)
			{
				tempSongLocation = new String(ch, start, length);
				readSongLocation = false;
			}
			else if (readSongGenre)
			{
				tempSongGenre = new String(ch, start, length);
				readSongGenre = false;
			}
			else if (readSongTrackNumber)
			{
				tempSongTrackNumber = Integer.parseInt(new String(ch, start, length));
				readSongTrackNumber = false;
			}
			else if (readSongRating)
			{
				tempSongRating = Integer.parseInt(new String(ch, start, length));
				readSongRating = false;
			}
			else if (readSongDiscNumber)
			{
				tempSongDiscNumber = Integer.parseInt(new String(ch, start, length));
				readSongDiscNumber = false;
			}
			else if (readSongBitRate)
			{
				tempSongBitRate = Integer.parseInt(new String(ch, start, length));
				readSongBitRate = false;
			}
			else if (readSongAlbumArtist)
			{
				tempSongAlbumArtist = new String(ch, start, length);
				readSongAlbumArtist = false;
			}
			else if (readSongComments)
			{
				tempSongComments = new String(ch, start, length);
				readSongComments = false;
			}
			else if (readSongBPM)
			{
				tempSongBPM = Integer.parseInt(new String(ch, start, length));
				readSongBPM = false;
			}
		}

		/**
		 * @see org.xml.sax.helpers.DefaultHandler#endElement(String uri,
		 * String localName, String qName)
		 */
		public void endElement(String uri, String localName,
				String qName) throws SAXException 
		{
			//A song is processed when the end of its entry in a Rhythmbox
			//library is reached.
			if (qName.equals("entry") && readSong)
			{
				//A Song object is created with all of a song's information
				//that was read, and it is added to the array list of songs.
				Song tempSong = new Song(tempSongTitle, tempSongArtist,
						tempSongAlbum, tempSongLocation, tempSongGenre, 
						tempSongTrackNumber, tempSongRating, tempSongDiscNumber,
						tempSongBitRate, tempSongAlbumArtist, tempSongComments, 
						tempSongBPM);
				songLibrary.add(tempSong);

				//The temporary variables are reinitialized.
				tempSongTitle = "";
				tempSongArtist = "";
				tempSongAlbum = "";
				tempSongLocation = "";
				tempSongGenre = "";
				tempSongTrackNumber = 0;
				tempSongRating = 0;
				tempSongDiscNumber = 0;
				tempSongBitRate = 0;
				tempSongAlbumArtist = "";
				tempSongComments = "";
				tempSongBPM = 0;
				readSong = false;
			}
		}
	}
}



