package com.vr.app.sh.domain.model

import java.io.Serializable

data class ResultTest(
    var test_id:Int = 0,
    var num_correct_answer:Int = 0,
    var num_wrong_answer:Int = 0,
    var num_not_answer:Int = 0,
    var all_result:Int = 0
): Serializable