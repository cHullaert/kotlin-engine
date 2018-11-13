import com.darwinit.experiment.kotlin.Application

// this class can be implemented to be used
class Greeter(val name : String) {
    fun greet() {
        println("Hello, ${name}");
    }
}

// this method is invokable from outside
fun Hello(name: String): String {
    println("this method is invocable")
    val greeter = Greeter("inv"+name)
    greeter.greet()

    return "succeed"
}

// with this method, I can pass a complex value from outside
fun View(application: Application) {
    println("application: "+application.computeValue())
}

// example to get binding value - evaluate at the evaluation
println("evaluation of the script")
val greeter=Greeter(bindings["name"] as String)
greeter.greet()
