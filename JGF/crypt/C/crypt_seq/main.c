/* 
 * File:   main.c
 * Author: Bruno Medeiros
 *
 * Created on 14 de Outubro de 2016, 17:22
 */

/**
 * 
 * @author Bruno Medeiros
 * 
 * This code is the result of reusing the Crypt case study from 
 * JGF JAVA Benchmarks and adapting it to C.
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "Crypt.h"

/*
 * 
 */
int main(int argc, char** argv) {

    // If the user wants to validate the simulation 
    int validation          = (argc > 1) ? (strcmp(argv[1],"-v") == 0) :1;	
    int size                = (argc > 2) ? atoi(argv[2]) : 0;   // dim problem
    
    run(size, validation);
    
    return (EXIT_SUCCESS);
}

