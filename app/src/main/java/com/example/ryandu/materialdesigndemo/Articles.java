package com.example.ryandu.materialdesigndemo;

/**
 * <p>Project:MaterialDesignDemo</p>
 * <p>Package:com.example.ryandu.materialdesigndemo</p>
 * <p>Description:</p>
 * <p>Company:</p>
 *
 * @author duyangs
 * @date 2017/09/07 0007
 */
public class Articles {

    private String name;
    private String imageUrl;

    public Articles(String name, String imageId) {
        this.name = name;
        this.imageUrl = imageId;
    }

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    @Override
    public String toString() {
        return "Articles{" +
                "name='" + name + '\'' +
                ", imageUrl=" + imageUrl +
                '}';
    }
}
