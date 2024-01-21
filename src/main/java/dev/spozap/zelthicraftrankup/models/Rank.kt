package dev.spozap.zelthicraftrankup.models

class Rank(val id: String,
           val requirements: List<RankupRequirement>,
           val displayName: String,
           val lore: List<String>,
           val skullTexture: String)