package com.wy.moodindex.dao;

import com.wy.moodindex.pojo.PostDaily;
import com.wy.moodindex.pojo.PostDailyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PostDailyMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table post_daily
     *
     * @mbg.generated
     */
    long countByExample(PostDailyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table post_daily
     *
     * @mbg.generated
     */
    int deleteByExample(PostDailyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table post_daily
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table post_daily
     *
     * @mbg.generated
     */
    int insert(PostDaily record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table post_daily
     *
     * @mbg.generated
     */
    int insertSelective(PostDaily record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table post_daily
     *
     * @mbg.generated
     */
    List<PostDaily> selectByExample(PostDailyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table post_daily
     *
     * @mbg.generated
     */
    PostDaily selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table post_daily
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") PostDaily record, @Param("example") PostDailyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table post_daily
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") PostDaily record, @Param("example") PostDailyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table post_daily
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(PostDaily record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table post_daily
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(PostDaily record);
}