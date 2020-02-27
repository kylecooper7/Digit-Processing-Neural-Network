import java.net.URL;

public class TheURL {
 protected URL url;
	public TheURL(String fileName) {
		url = getClass().getResource("/resources/" + fileName);
	}
	public URL getUrl() {
		return url;
	}
	public void setUrl(URL url) {
		this.url = url;
	}


}


