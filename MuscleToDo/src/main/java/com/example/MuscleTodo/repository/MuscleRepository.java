package com.example.MuscleTodo.repository;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.MuscleTodo.service.MuscleEntity;
import com.example.MuscleTodo.service.MuscleSearchEntity;

@Mapper
public interface MuscleRepository {

	@Select("""
			<script>
				SELECT id, doTime, traName, traWei, traSet, traTimes, traType 
				FROM muscle
				<where>
					<if test='condition.muscleTypes != null and !condition.muscleTypes.isEmpty()'>
						 traType IN (
							<foreach item='item' collection='condition.muscleTypes' separator=','>
                              #{item}
                            </foreach>
                        )
					</if>
				</where>
			</script>

						""")
	List<MuscleEntity> select(@Param("condition") MuscleSearchEntity condition);

	@Insert("""
			INSERT INTO muscle (doTime, traName, traWei, traSet, traTimes, traType)
			VALUES(#{list.doTime},#{list.traName},#{list.traWei},#{list.traSet},#{list.traTimes},#{list.traType})
			""")
	void insert(@Param("list") MuscleEntity muscleEntity);

	@Select("SELECT id, doTime, traName, traWei, traSet, traTimes, traType FROM muscle WHERE id = #{id}")
	Optional<MuscleEntity> findById(@Param("id") int id);

	@Update("""
			UPDATE muscle
			SET
				doTime = #{list.doTime},
				traName = #{list.traName},
				traWei = #{list.traWei},
				traSet = #{list.traSet},
				traTimes = #{list.traTimes},
				traType = #{list.traType}
			WHERE
				id = #{list.id}
			""")
	void update(@Param("list") MuscleEntity muscleEntity);

	@Delete("DELETE FROM muscle WHERE id = #{id}")
	void delete(@Param("id") int id);
}
