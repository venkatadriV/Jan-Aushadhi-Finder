package com.janaushadhi.finder.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.janaushadhi.finder.data.SeedData
import com.janaushadhi.finder.data.model.Favorite
import com.janaushadhi.finder.data.model.Medicine
import com.janaushadhi.finder.data.model.Reminder
import com.janaushadhi.finder.data.model.SearchHistory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Room database for Jan-Aushadhi Finder app.
 * Pre-populated with 500+ medicine records on first creation.
 */
@Database(
    entities = [Medicine::class, Favorite::class, Reminder::class, SearchHistory::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun medicineDao(): MedicineDao
    abstract fun favoriteDao(): FavoriteDao
    abstract fun reminderDao(): ReminderDao
    abstract fun searchHistoryDao(): SearchHistoryDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        /**
         * Get or create the database instance.
         * Seeds medicine data on first creation.
         */
        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "janaushadhi_database"
                )
                .addCallback(object : RoomDatabase.Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        // Seed the database with medicine data on first creation
                        INSTANCE?.let { database ->
                            CoroutineScope(Dispatchers.IO).launch {
                                seedDatabase(database.medicineDao())
                            }
                        }
                    }
                })
                .fallbackToDestructiveMigration()
                .build()
                INSTANCE = instance
                instance
            }
        }

        /**
         * Populate the database with 500+ medicine records
         */
        private suspend fun seedDatabase(medicineDao: MedicineDao) {
            val medicines = SeedData.getMedicines()
            medicineDao.insertAll(medicines)
        }
    }
}
