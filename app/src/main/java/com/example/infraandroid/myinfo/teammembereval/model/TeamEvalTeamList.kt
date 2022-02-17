package com.example.infraandroid.myinfo.teammembereval.model

import com.example.infraandroid.myinfo.teammembereval.model.TeamEvalMemberList

data class TeamEvalTeamList(
    var category : String,
    var title : String,
    var date : String,
    var innerlist : MutableList<TeamEvalMemberList>
)
