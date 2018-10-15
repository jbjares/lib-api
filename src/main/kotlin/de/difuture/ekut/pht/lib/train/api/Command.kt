package de.difuture.ekut.pht.lib.train.api

enum class Command(val repr: String) {

     CHECK_REQUIREMENTS("check_requirements"),
     LIST_REQUIREMENTS("list_requirements"),
     PRINT_SUMMARY("print_summary"),
     RUN_ALGORITHM("run_algorithm")
}
