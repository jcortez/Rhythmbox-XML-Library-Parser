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

/**
 * A class to create a Song object that will hold a song's information such as 
 * title, artist, album, bitrate, and its location when parsing song entries 
 * in a Rhythmbox's XML library database.
 */
public class Song 
{
	//The song's title.
	private String title;
	//The song's artist.
	private String artist;
	//The song's album.
	private String album;
	//The song's hard drive location.
	private String location;
	//The song's genre.
	private String genre;
	//The song's track number.
	private int trackNum;
	//The song's rating.
	private int rating;
	//The song's disc number that it is on.
	private int discNum;
	//The song's bitrate.
	private int bitRate;
	//The song's album artist.
	private String albumArtist;
	//The song's comments.
	private String comments;
	//The song's BPM (beats per minute).
	private int bpm;
	
	public Song(String title, String artist, String album, String location, 
			String genre, int trackNum, int rating, int discNum, int bitRate,
			String albumArtist, String comments, int bpm)
	{
		this.title = title;
		this.artist = artist;
		this.album = album;
		this.location = location;
		this.genre = genre;
		this.trackNum = trackNum;
		this.rating = rating;
		this.discNum = discNum;
		this.bitRate = bitRate;
		this.albumArtist = albumArtist;
		this.comments = comments;
		this.bpm = bpm;
	}
	
	/**
	 * @return A song's title.
	 */
	public String getTitle()
	{
		return title;
	}
	
	/**
	 * @return A song's artist.
	 */
	public String getArtist()
	{
		return artist;
	}
	
	/**
	 * @return A song's album.
	 */
	public String getAlbum()
	{
		return album;
	}
	
	/**
	 * @return A song's hard drive location.
	 */
	public String getLocation()
	{
		return location;
	}
	
	/**
	 * @return A song's genre.
	 */
	public String getGenre()
	{
		return genre;
	}
	
	/**
	 * @return A song's track number.
	 */
	public int getTrackNumber()
	{
		return trackNum;
	}
	
	/**
	 * @return A song's rating.
	 */
	public int getRating()
	{
		return rating;
	}
	
	/**
	 * @return A song's disc number that it is on.
	 */
	public int getDiscNumber()
	{
		return discNum;
	}
	
	/**
	 * @return A song's bit rate.
	 */
	public int getBitRate()
	{
		return bitRate;
	}
	
	/**
	 * @return A song's album artist.
	 */
	public String getAlbumArtist()
	{
		return albumArtist;
	}
	
	/**
	 * @return A song's comments.
	 */
	public String getComments()
	{
		return comments;
	}
	
	/**
	 * @return A song's BPM (beats per minute).
	 */
	public int getBPM()
	{
		return bpm;
	}
}
