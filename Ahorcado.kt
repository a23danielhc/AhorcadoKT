import java.awt.geom.CubicCurve2D
import java.lang.reflect.Array
import kotlin.random.Random
fun printPalabraCubierta(palabra: List<String>) : String{
    var palabraFinal = ""
    for (i in 0..palabra.size-1){
        palabraFinal += palabra[i]
    }
    return palabraFinal
}

fun main () {

    // Se reproduce la música, como tarda, se deja 2 segundos de delay para que comience antes del programa
    val musica = ReproductorMidi("pugnodollari.mid")
    musica.sequencer
    Thread.sleep(5000)
    //Creamos la palabra y las variables donde se almacena y donde se irá descubriendo la palabra oculta
    val dictFrutas = listOf("manzana")
    val palabra : String = dictFrutas.random()
    var palabraCubierta = mutableListOf<String>()
    for (i in 1..palabra.length){
        palabraCubierta.add("*")
    }
    //Presentación del programa y comienzo del bucle jugable
    println("Bienvenido al juego del ahorcado")


    var intentoNumero = 0
    var indiceActual : Int

    while (intentoNumero < 7) {
        println("La a adivinar es ahora mismo: " + printPalabraCubierta(palabraCubierta))
        print("Por favor, introduce una letra que creas que está dentro de la palabra a adivinar: ")
        indiceActual = 0
        var inputLetra = readln().split(' ').first().lowercase()
        if (inputLetra.length != 1){
            println("Por favor, inserte una sola letra, mayúscula o minúscula")
            continue
        }
        if (!palabra.contains(inputLetra)) {
            intentoNumero += 1
            DibujoAhorcado.dibujar(intentoNumero)
            println("Has fallado, te quedan " + (7 - intentoNumero) + " intentos")
            continue
        }
        while (indiceActual < palabra.length){
            indiceActual = palabra.indexOf(inputLetra, indiceActual)
            palabraCubierta.set(indiceActual, inputLetra)
            if (palabra.lastIndexOf(inputLetra) == indiceActual){
                break
            }
            indiceActual += 1
        }
        if (printPalabraCubierta(palabraCubierta) == palabra) {
            println("Has acertado la palabra " + printPalabraCubierta(palabraCubierta))
            break
        }
        println("Has acertado la letra, te quedan " + (7 - intentoNumero) + " intentos")
    }
    print("Muchas gracias por jugar :)")
    musica.cerrar()
}