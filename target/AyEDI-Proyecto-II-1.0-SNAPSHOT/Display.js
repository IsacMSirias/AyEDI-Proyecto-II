class Display {

    constructor(displayValorAnterior, displayValorActual) {

        this.displayValorAnterior = displayValorAnterior;
        this.displaValorActual = displayValorActual;

        this.valorActual= "";
        this.valorAnterior = "";

    }

    borrar(){
        this.valorActual = this.valorActual.toString().slice(0,-1);
        this.printValores();
    }

    borrarTodo(){
        this.valorActual = "";
        this.valorAnterior = "";
        this.printValores();
    }

    agregarNumeros(num){
    if (num === "." && this.valorActual.includes(".")) return;
        this.valorActual = this.valorActual.toString() + num;
        this.printValores()

    }


    agregarOperador(oper){
        this.valorActual = this.valorActual.toString() + oper;
        this.printValores()

    }

    printValores(){

        this.displaValorActual.textContent = this.valorActual;
        this.displayValorAnterior.textContent = this.valorAnterior;

    }



}