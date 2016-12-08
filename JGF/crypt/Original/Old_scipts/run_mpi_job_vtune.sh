# PBS -N 
#PBS -l walltime=00:05:00
#PBS -q mei

# PBS -m abe
# PBS -M nuno.oliv.94@gmail.com

#PBS -l nodes=1:r662:ppn=4

# Directories
Project_Folder="/home/a67649/tese/benchmarks/ScholarShip_public/JGF/Sparse/JGF_original"
Vtune_output_dir="/home/a67649/tese/benchmarks/ScholarShip_public/JGF/Sparse/JGF_original/Vtune_out/mpi"
# Inicializar Java (Não Necessario)

# Inicializar VTune
source /share/apps/intel/vtune_amplifier_xe_2017/amplxe-vars.sh
source /share/apps/intel/vtune_amplifier_xe_2017/sep_vars.sh

# Ir Para Pasta
cd $Project_Folder

# Compilar
mkdir -p classes
# seq
javac -g -d ./classes ./mpi/*.java
# Correr com VTune
# Criar pasta
mkdir -p "/home/a67649/tese/benchmarks/ScholarShip_public/JGF/Sparse/JGF_original/Vtune_out/mpi"
cd $Vtune_output_dir

echo "Collecting HotSpots"
(>&2 echo "Collecting hotspots")
amplxe-cl -collect hotspots -- java -Xcomp -Djava.library.path=native_lib/ia32 -cp ${Project_Folder}/classes/ mpi.JGFSparseMatmultBenchSizeA -size 5
echo "Collecting Memory-access"
(>&2 echo "Collecting Memory_Acess")
amplxe-cl -collect memory-access -- java -Xcomp -Djava.library.path=native_lib/ia32 -cp ${Project_Folder}/classes/ mpi.JGFSparseMatmultBenchSizeA -size 5
echo "Collecting Disk-io"
(>&2 echo "Collecting Disk-io")
amplxe-cl -collect disk-io -- java -Xcomp -Djava.library.path=native_lib/ia32 -cp ${Project_Folder}/classes/ mpi.JGFSparseMatmultBenchSizeA -size 5
