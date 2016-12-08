/* 
 * File:   main.c
 * Author: Bruno Medeiros
 *
 * Created on 14 de Outubro de 2016, 17:22
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "Crypt.h"

/**
 * 
 * @author Bruno Medeiros
 * 
 * This code is the result of reusing the Crypt case study from 
 * JGF JAVA Benchmarks and adapting it to C.
 */

int main(int argc, char** argv) {

    // If the user wants to validate the simulation 
    int validation          = (argc > 1) ? (strcmp(argv[1],"-v") == 0) :1;	
    int size                = (argc > 2) ? atoi(argv[2]) : 0;   // dim problem
    int numThreads          = (argc > 3) ? atoi(argv[3]) : 2;
    
    run(size, validation, numThreads);
    
    return (EXIT_SUCCESS);
}

