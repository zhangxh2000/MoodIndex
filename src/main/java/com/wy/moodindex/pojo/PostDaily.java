package com.wy.moodindex.pojo;

import java.util.Date;

public class PostDaily {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column post_daily.id
     *
     * @mbg.generated
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column post_daily.stock_id
     *
     * @mbg.generated
     */
    private String stockId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column post_daily.count
     *
     * @mbg.generated
     */
    private Integer count;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column post_daily.date
     *
     * @mbg.generated
     */
    private Date date;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table post_daily
     *
     * @mbg.generated
     */
    public PostDaily(Integer id, String stockId, Integer count, Date date) {
        this.id = id;
        this.stockId = stockId;
        this.count = count;
        this.date = date;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table post_daily
     *
     * @mbg.generated
     */
    public PostDaily() {
        super();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column post_daily.id
     *
     * @return the value of post_daily.id
     *
     * @mbg.generated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column post_daily.id
     *
     * @param id the value for post_daily.id
     *
     * @mbg.generated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column post_daily.stock_id
     *
     * @return the value of post_daily.stock_id
     *
     * @mbg.generated
     */
    public String getStockId() {
        return stockId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column post_daily.stock_id
     *
     * @param stockId the value for post_daily.stock_id
     *
     * @mbg.generated
     */
    public void setStockId(String stockId) {
        this.stockId = stockId == null ? null : stockId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column post_daily.count
     *
     * @return the value of post_daily.count
     *
     * @mbg.generated
     */
    public Integer getCount() {
        return count;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column post_daily.count
     *
     * @param count the value for post_daily.count
     *
     * @mbg.generated
     */
    public void setCount(Integer count) {
        this.count = count;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column post_daily.date
     *
     * @return the value of post_daily.date
     *
     * @mbg.generated
     */
    public Date getDate() {
        return date;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column post_daily.date
     *
     * @param date the value for post_daily.date
     *
     * @mbg.generated
     */
    public void setDate(Date date) {
        this.date = date;
    }
}