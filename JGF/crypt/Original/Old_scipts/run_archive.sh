#!/bin/bash

# get date and stamp
DATE=$(date +%d-%m-%Y_%H-%M-%S)
timestamp="$(date +%s)"

Search_folder="/home/a67649/tese/benchmarks/ScholarShip_public/JGF/Sparse"

mkdir -p /home/a67649/tese/benchmarks/ScholarShip_public/JGF/Sparse/bak/${DATE}_${timestamp} 
mv ${Search_folder}/run_*.sh.* /home/a67649/tese/benchmarks/ScholarShip_public/JGF/Sparse/bak/${DATE}_${timestamp} 
mv ${Search_folder}/JGF_original/Vtune_out /home/a67649/tese/benchmarks/ScholarShip_public/JGF/Sparse/bak/${DATE}_${timestamp}
