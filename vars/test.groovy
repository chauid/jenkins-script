import com.example.Greeter

def call(String name) {
    def greeter = new Greeter(steps)  // steps는 Jenkins DSL context
    greeter.sayHello(name)
}

def greet(String name = 'World') {
    echo "Greetings, ${name}!"
}
def farewell(String name = 'World') {
    echo "Goodbye, ${name}!"
}

def printSomething(String name = 'Nothing') {
    echo "Printing something for ${name}!"
}

def sum(int x = 0, int y = 0) {
    return x + y
}
