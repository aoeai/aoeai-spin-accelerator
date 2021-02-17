<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${mapperClass.packageName}.${mapperClass.className}">

    <!-- 插入数据 -->
    <insert id="insert" parameterType="${mapperClass.po.packageName}.${mapperClass.po.className}" keyProperty="id" useGeneratedKeys="true">
        <include refid="insertSQL" />
    </insert>

    <!-- 插入批量数据 -->
    <insert id="insertBatch">
        INSERT INTO ${mapperClass.po.table.name}
            <trim prefix="(" suffix=")" suffixOverrides=",">
                <include refid="ALL_COLUMNS_EXCEPT_PRIMARY_KEY_TEST_NULL" />
            </trim>
        VALUES
        <foreach collection="list" item="item" index="index" separator="," >
            <trim prefix="(" suffix=")" suffixOverrides=",">
                <include refid="ALL_VALUES_EXCEPT_PRIMARY_KEY_TEST_NULL" />
            </trim>
        </foreach>
    </insert>

    <!-- 插入或更新数据 -->
    <insert id="insertOrUpdate" parameterType="${mapperClass.po.packageName}.${mapperClass.po.className}">
        <selectKey keyProperty="count" resultType="int" order="BEFORE">
            SELECT COUNT(*)
            FROM ${mapperClass.po.table.name}
            <#list mapperClass.po.table.columns as column>
                <#if column.isPrimaryKey == true>
            WHERE ${column.name} = #${r'{'}${column.name}}
                </#if>
            </#list>
        </selectKey>
        <if test="count == 0">
            <include refid="insertSQL" />
        </if>
        <if test="count > 0">
            UPDATE ${mapperClass.po.table.name}
            <include refid="UPDATE_SET" />
            <#list mapperClass.po.table.columns as column>
                <#if column.isPrimaryKey == true>
            WHERE ${column.name} = #${r'{'}${column.name}}
                </#if>
            </#list>
        </if>
    </insert>

    <!-- 更新数据 -->
    <update id="update">
        UPDATE ${mapperClass.po.table.name}
        <include refid="UPDATE_SET" />
        <include refid="queryCondition" />
    </update>

    <!-- 查询一条记录 -->
    <select id="selectOne" resultType="${mapperClass.po.packageName}.${mapperClass.po.className}">
        SELECT
        <include refid="ALL_COLUMNS" />
        FROM ${mapperClass.po.table.name}
        <include refid="queryCondition" />
        LIMIT 1
    </select>

    <!-- 查询所有记录 -->
    <select id="selectList" resultType="${mapperClass.po.packageName}.${mapperClass.po.className}">
        SELECT
        <include refid="ALL_COLUMNS" />
        FROM ${mapperClass.po.table.name}
        <include refid="queryCondition" />
    </select>

    <!-- 查询总数 -->
    <select id="selectCount" resultType="int">
        SELECT COUNT(*)
        FROM ${mapperClass.po.table.name}
        <include refid="queryCondition" />
    </select>

    <!-- 查询列表(分页) -->
    <select id="selectPageList" resultType="${mapperClass.po.packageName}.${mapperClass.po.className}">
        SELECT
        <include refid="ALL_COLUMNS" />
        FROM ${mapperClass.po.table.name}
        <include refid="queryCondition" />
        ORDER BY create_time DESC
    </select>

    <!-- id:DO Map -->
    <select id="selectMap" resultType="java.util.Map">
        SELECT
        <include refid="ALL_COLUMNS" />
        FROM ${mapperClass.po.table.name}
        WHERE id IN
        <foreach item="item" index="index" collection="ids" open="(" separator="," close=")">
            ${r'#{item}'}
        </foreach>
    </select>

    <!-- 所有列 -->
    <sql id="ALL_COLUMNS">
        <#list mapperClass.po.table.columns as column>
            ${column.name}<#if column_has_next>,</#if>
        </#list>
    </sql>

    <!-- 查询条件 -->
    <sql id="queryCondition">
        <where>
            <foreach collection="conditionList" index="index" item="item">
                <choose>
                    <when test="item.condition == 'AND'">
                        ${r'AND ${item.column} = #{item.value}'}
                    </when>
                    <when test="item.condition == 'GE'">
                        ${r'AND ${item.column} <![CDATA[>=]]> #{item.value}'}
                    </when>

                    <when test="item.condition == 'NE'">
                        ${r'AND ${item.column} <![CDATA[<>]]> #{item.value}'}
                    </when>
                    <when test="item.condition == 'GT'">
                        ${r'AND ${item.column} <![CDATA[>]]> #{item.value}'}
                    </when>
                    <when test="item.condition == 'LT'">
                        ${r'AND ${item.column} <![CDATA[<]]> #{item.value}'}
                    </when>
                    <when test="item.condition == 'LE'">
                        ${r'AND ${item.column} <![CDATA[<=]]> #{item.value}'}
                    </when>
                    <when test="item.condition == 'LIKE'">
                        AND ${r'${item.column}'} LIKE CONCAT('%',${r'#{item.value}'},'%')
                    </when>
                    <when test="item.condition == 'IN'">
                        AND ${r'${item.column}'} IN
                        <foreach collection="item.value" item="itemValue" index="index" open="(" close=")" separator=",">
                            ${r'#{itemValue}'}
                        </foreach>
                    </when>
                    <when test="item.condition == 'APPLY'">
                        AND ${r'${item.value}'}
                    </when>
                </choose>
            </foreach>
        </where>
    </sql>

    <!-- 插入语句 -->
    <sql id="insertSQL">
        INSERT INTO ${mapperClass.po.table.name}
        <trim prefix="(" suffix=")" suffixOverrides=",">
            <include refid="ALL_COLUMNS_EXCEPT_PRIMARY_KEY_TEST_NULL" />
        </trim>
        <trim prefix="VALUES (" suffix=")" suffixOverrides=",">
            <include refid="ALL_VALUES_EXCEPT_PRIMARY_KEY_TEST_NULL" />
        </trim>
    </sql>

    <!-- 所有列是否为(不包含主键) null -->
    <sql id="ALL_COLUMNS_EXCEPT_PRIMARY_KEY_TEST_NULL">
        <#list mapperClass.po.table.columns as column>
            <#if column.isPrimaryKey == false>
            <if test="${column.humpName} != null">
                ${column.name},
            </if>
            </#if>
        </#list>
    </sql>

    <!-- 所有列的值是否为(不包含主键) null -->
    <sql id="ALL_VALUES_EXCEPT_PRIMARY_KEY_TEST_NULL">
        <#list mapperClass.po.table.columns as column>
            <#if column.isPrimaryKey == false>
            <if test="${column.humpName} != null">
                #${r'{'}${column.humpName}},
            </if>
            </#if>
        </#list>
    </sql>

    <!-- UPDATE语句所有列和值值是否为(不包含主键) null -->
    <sql id="UPDATE_SET">
        <set>
        <#list mapperClass.po.table.columns as column>
        <#if column.isPrimaryKey == false && column.name != 'create_time'>
        <if test="po.${column.humpName} != null">
            ${column.name} = #${r'{'}po.${column.humpName}},
        </if>
        </#if>
        </#list>
        </set>
    </sql>

</mapper>