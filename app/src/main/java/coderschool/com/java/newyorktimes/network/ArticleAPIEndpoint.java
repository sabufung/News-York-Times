package coderschool.com.java.newyorktimes.network;

import coderschool.com.java.newyorktimes.models.SearchResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by BuuPV on 2/24/2017.
 */

public interface ArticleAPIEndpoint {

    @GET("articlesearch.json")
    Call<SearchResponse> getArticle(@Query("q") String q, @Query("begin_date") String begin_date, @Query("fq") String fq, @Query("page") int page);
}
