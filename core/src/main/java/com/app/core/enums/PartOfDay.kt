package com.app.core.enums

enum class PartOfDay(val value: String) {
    DAY("d"),
    NIGHT("n");

    override fun toString(): String {
        return this.value;
    }
}
