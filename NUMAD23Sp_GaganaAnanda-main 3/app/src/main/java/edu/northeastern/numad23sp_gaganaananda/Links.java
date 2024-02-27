package edu.northeastern.numad23sp_gaganaananda;

public class Links {

    private String name;

    private String link;

    /**
     * Constructs a person object with the specified name and age.
     *  @param name - name of the website.
     * @param link -  link to the website.
     */
    public Links(String name, String link) {
        this.name = name;
        this.link = link;
    }

    public String getName() {
        return name;
    }

    public String getLink() {
        return link;
    }


}
