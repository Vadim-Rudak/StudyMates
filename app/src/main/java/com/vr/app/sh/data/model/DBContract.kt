package com.vr.app.sh.data.model

import android.provider.BaseColumns
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

object DBContract {

    class UserEntry : BaseColumns {
        companion object {
            val TABLE_NAME = "User"
            val COLUMN_ID = "id"
            val COLUMN_USER_NAME = "username"
            val COLUMN_USER_ROLE = "role"
        }
    }

    class BookEntry : BaseColumns {
        companion object {
            val TABLE_NAME = "Books"
            val COLUMN_ID = "id"
            val COLUMN_NUM_CLASS = "numclass"
            val COLUMN_NAME_BOOK = "namebook"
            val COLUMN_IMAGE_BOOK = "imagebook"
        }
    }

    class TestEntry : BaseColumns {
        companion object {
            val TABLE_NAME = "Tests"
            val COLUMN_ID = "id"
            val COLUMN_SUBJECT = "subject"
            val COLUMN_NUM_CLASS = "numclass"
            val COLUMN_NAME_TEST = "nametest"
            val COLUMN_NUM_QUESTIONS = "numquestions"
        }
    }

    class QuestionEntry : BaseColumns {
        companion object {
            val TABLE_NAME = "Questions"
            val COLUMN_ID = "id"
            val COLUMN_QUESTION = "question"
            val COLUMN_OTV_1 = "otv1"
            val COLUMN_OTV_2 = "otv2"
            val COLUMN_OTV_3 = "otv3"
            val COLUMN_OTV_4 = "otv4"
            val COLUMN_CORRECT_OTV = "correctOtv"
            val COLUMN_TEST_ID = "testid"
        }
    }

}