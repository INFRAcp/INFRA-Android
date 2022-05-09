package com.infra.infraandroid.myinfo.teammembereval.model

data class TeamEvalTeamList(
    var category : String,
    var title : String,
    var date : String,
    var innerlist : MutableList<TeamEvalMemberList>
)
