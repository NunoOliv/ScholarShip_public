#!/bin/bash

# get date and stamp
DATE=$(date +%d-%m-%Y_%H-%M-%S)
timestamp="$(date +%s)"

Search_folder="/home/a67649/tese/benchmarks/ScholarShip_public/JGF/series/improved"

mkdir -p /home/a67649/tese/benchmarks/ScholarShip_public/JGF/Sparse/bak/${DATE}_${timestamp} 
mv ${Search_folder}/sm/run_*.sh.* /home/a67649/tese/benchmarks/ScholarShip_public/JGF/series/improved/archived/${DATE}_${timestamp} 
mv ${Search_folder}/Vtune_out /home/a67649/tese/benchmarks/ScholarShip_public/JGF/series/improved/archived/${DATE}_${timestamp}
rm -r ${Search_folder}/classes_j*
