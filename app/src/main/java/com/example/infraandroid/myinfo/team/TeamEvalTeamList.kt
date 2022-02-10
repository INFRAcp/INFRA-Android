package com.example.infraandroid.myinfo.team

data class TeamEvalTeamList(
    var category : String,
    var title : String,
    var date : String,
    var innerlist : MutableList<TeamEvalMemberList>
)
