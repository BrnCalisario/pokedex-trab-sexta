package br.com.up.pokedex.model

data class Pokemon(
    val name:String,
    val url:String,
    val weight:Int,
    val stats: List<Stats>,
    val types: List<PokeType>,
    val abilities: List<Habilidade>,
    val moves: List<Move>
)

data class Move(
    val move: PokeMove
)

data class PokeMove(
    val name: String
)

data class Habilidade(
    val ability: PokeAbi
)

data class PokeAbi(
    val name: String,
)

data class PokeType (
    val type: PType
)

data class PType (
    val name: String
)

data class Stats(
    val base_stat: Int,
    val stat: StatType
)

data class StatType(
    val name: String
)


//data class PokemonFull(
//    val name: String,
//    val height: Int
//)

//data class Stats(
//    val hp: Int,
//    val attack: Int,
//    val defense: Int,
//    val speed: Int
//)
