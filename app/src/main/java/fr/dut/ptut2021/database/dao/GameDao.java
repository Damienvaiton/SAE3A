package fr.dut.ptut2021.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.ArrayList;
import java.util.List;

import fr.dut.ptut2021.models.databse.stats.game.DrawOnItData;
import fr.dut.ptut2021.models.databse.stats.game.PlayWithSoundData;
import fr.dut.ptut2021.models.databse.stats.game.WordWithHoleData;

@Dao
public interface GameDao {

    //WordWithHoleData
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertWWHData(WordWithHoleData wordWithHoleStats);

    @Query("SELECT MAX(dataId) FROM WordWithHoleData")
    int getWWHMaxId();

    @Update
    void updateWWHData(WordWithHoleData wordWithHoleStats);

    @Query("UPDATE WordWithHoleData SET lastUsed = 0 WHERE userId = :userId AND lastUsed = 1")
    void updateAllWWHDataLastUsed(int userId);

    @Query("SELECT * FROM WordWithHoleData WHERE userId = :userId AND word = :word AND syllable = :syllable")
    WordWithHoleData getWWHDataByData(int userId, String word, String syllable);

    @Query("SELECT * FROM WordWithHoleData WHERE userId = :userId")
    List<WordWithHoleData> getAllWWHData(int userId);

    default List<Integer> getAllWWHDataLastUsed(List<WordWithHoleData> listData, int lastUsed) {
        List<Integer> listInt = new ArrayList<>();

        for (int i = 0; i < listData.size(); i++) {
            if (listData.get(i).getLastUsed() == lastUsed) {
                listInt.add(i);
            }
        }
        return  listInt;
    }

    @Query("DELETE FROM WordWithHoleData WHERE userId = :userId")
    void deleteWWHDataByUser(int userId);



    //PlayWithSoundData
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertPWSData(PlayWithSoundData playWithSoundData);

    @Query("SELECT MAX(dataId) FROM PlayWithSoundData")
    int getPWSMaxId();

    @Update
    void updatePWSData(PlayWithSoundData playWithSoundData);

    @Query("UPDATE PlayWithSoundData SET lastUsed = 0 WHERE userId = :userId AND lastUsed = 1")
    void updateAllPWSDataLastUsed(int userId);

    @Query("SELECT * FROM PlayWithSoundData WHERE userId = :userId AND result = :result")
    PlayWithSoundData getPWSDataByResult(int userId, String result);

    @Query("SELECT * FROM PlayWithSoundData WHERE userId = :userId AND theme = :theme AND difficulty = :difficulty")
    List<PlayWithSoundData> getAllPWSDataByTheme(int userId, String theme, int difficulty);

    default List<Integer> getAllPWSDataLastUsed(List<PlayWithSoundData> listData, int lastUsed) {
        List<Integer> listInt = new ArrayList<>();

            for (int i = 0; i < listData.size(); i++) {
                if (listData.get(i).getLastUsed() == lastUsed) {
                    listInt.add(i);
                }
            }
        return  listInt;
    }

    @Query("DELETE FROM PlayWithSoundData WHERE userId = :userId")
    void deletePWSDataByUser(int userId);


    //DrawOnItData
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertDOIData(DrawOnItData drawOnItData);

    @Update
    void updateDOIData(DrawOnItData drawOnItData);

    @Query("UPDATE DrawOnItData SET lastUsed = 0 WHERE userId = :userId")
    void updateAllDOIDataLastUsed(int userId);

    @Query("SELECT * FROM DrawOnItData WHERE userId = :userId AND draw = :draw")
    DrawOnItData getDOIData(int userId, String draw);

    @Query("SELECT * FROM DrawOnItData WHERE userId = :userId")
    List<DrawOnItData> getAllDOIData(int userId);

    default List<Integer> getAllDOIDataLastUsed(List<DrawOnItData> listData, boolean lastUsed) {
        List<Integer> listInt = new ArrayList<>();
        if (lastUsed) {
            for (int i = 0; i < listData.size(); i++) {
                if (listData.get(i).isLastUsed()) {
                    listInt.add(i);
                }
            }
        }
        else {
            for (int i = 0; i < listData.size(); i++) {
                if (!listData.get(i).isLastUsed()) {
                    listInt.add(i);
                }
            }
        }
        return  listInt;
    }

    @Query("DELETE FROM DrawOnItData WHERE userId = :userId")
    void deleteDOIDataByUser(int userId);

}
