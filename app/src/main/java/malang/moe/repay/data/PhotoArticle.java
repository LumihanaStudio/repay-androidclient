package malang.moe.repay.data;

/**
 * Created by Junseok on 2015-12-16.
 */
public class PhotoArticle {
    public String title, content, apikey, articleKey;

    public PhotoArticle(String title, String content, String apikey, String articleKey) {
        this.title = title;
        this.content = content;
        this.apikey = apikey;
        this.articleKey = articleKey;
    }
}
