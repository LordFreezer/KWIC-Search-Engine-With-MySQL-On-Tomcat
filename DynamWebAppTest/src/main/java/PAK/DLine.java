package PAK;

/**
 * @author Chad Marshall
 *
 */
/**
 * The Class DLine.
 */

public class DLine {

	/**
	 * OVERVIEW: Declaration of description and url parts of admin user info
	 */
	private String desc, url;

	/**
	 * OVERVIEW: Default constructor for a line.
	 */
	public DLine() {
		super();
	}

	/**
	 * OVERVIEW: Instantiates a new line.
	 *
	 * @param desc the desc
	 * @param url  the url
	 */
	public DLine(String desc, String url) {
		super();
		this.desc = desc;
		this.url = url;
	}

	/**
	 * OVERVIEW: Gets the desc.
	 *
	 * @return the desc
	 */
	public String getDesc() {
		return desc;
	}

	/**
	 * OVERVIEW: Sets the desc.
	 *
	 * @param desc the new desc
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}

	/**
	 * OVERVIEW: Gets the url.
	 *
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * OVERVIEW: Sets the url.
	 *
	 * @param url the new url
	 */
	public void setUrl(String url) {
		this.url = url;
	}

}
