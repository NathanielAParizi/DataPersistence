package com.example.datapersistence


// Constants for SQLite Databse
//

const val DATABASE_NAME = "data_per_database"
const val TABLE_NAME = "person"
const val DATABASE_VERSION = 1

const val COLUMN_FIRSTNAME = "first_name"
const val COLUMN_LASTNAME = "last_name"
const val COLUMN_SSN = "ssn"

const val CREATE_PERSON_TABLE = "{" +
        " CREATE TABLE $TABLE_NAME + {" +
        "$COLUMN_FIRSTNAME String," +
        "$COLUMN_LASTNAME String," +
        "$COLUMN_SSN String PRIMARY_KEY" +
        "}"



