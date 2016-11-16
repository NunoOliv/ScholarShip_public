/* 
 * File:   functions_mpi.h
 * Author: Bruno Medeiros.
 *
 * Created on 13 de Outubro de 2016, 18:56
 */

#ifndef FUNCTIONS_MPI_H
#define FUNCTIONS_MPI_H

#include "Crypt.h"

int getProcessId        ();
int numberProcess       ();
int master              ();
void scattering         (Ideatest *ideatest, int rank, int numberProcesses);
void sendSpecialType    (int slaveID, int dim, int offset, byte *plain1);
void gathering          (Ideatest *ideatest, int rank, int numberProcesses);
void recvSpecialType    (int slaveID, int dim, int offset, byte *plain2);
#ifdef __cplusplus
extern "C" {
#endif




#ifdef __cplusplus
}
#endif

#endif /* FUNCTIONS_MPI_H */

