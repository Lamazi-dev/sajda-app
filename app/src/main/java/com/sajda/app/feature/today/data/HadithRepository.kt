package com.sajda.app.feature.today.data

import com.sajda.app.feature.today.DailyHadith

object HadithRepository {

    fun getAllHadiths(): List<DailyHadith> {
        return hadithMockData
    }

    fun getDailyHadith(): DailyHadith {
        return hadithMockData.random()
    }

    fun getHadithById(id: Int): DailyHadith? {
        return hadithMockData.firstOrNull { hadith ->
            hadith.id == id
        }
    }

    fun getHadithsByTopic(topic: String): List<DailyHadith> {
        return hadithMockData.filter { hadith ->
            hadith.topic == topic
        }
    }
}