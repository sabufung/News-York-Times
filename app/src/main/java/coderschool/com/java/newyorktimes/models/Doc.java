package coderschool.com.java.newyorktimes.models;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Data;

/**
 * Created by BuuPV on 2/24/2017.
 */

@Data
public class Doc {
    private String webUrl;
    private String snippet;
    private String leadParagraph;
    private Object _abstract;
    private Object printPage;
    private List<Object> blog = null;
    private String source;
    private List<Multimedium> multimedia = null;
    private Headline headline;
    private List<Keyword> keywords = null;
    private String pubDate;
    private String documentType;
    private Object newsDesk;
    private String sectionName;
    private String subsectionName;
    private String typeOfMaterial;
    private String id;
    private String wordCount;
    private Object slideshowCredits;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @Data
    public class Headline {
        private String main;
        private String name;
        private String contentKicker;
        private String kicker;
        private String printHeadline;
        private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    }

    @Data
    public class Keyword {
        private String rank;
        private String isMajor;
        private String name;
        private String value;
        private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    }

    @Data
    public class Multimedium {
        private String credit;
        private String url;
        private String rank;
        private Integer height;
        private String subtype;
        private String type;
        private Integer width;
        private String caption;
        private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    }
}