#PBS -N 
#PBS -l walltime=00:01:00
#PBS -q mei

# #PBS -m abe
# #PBS -M nuno.oliv.94@gmail.com

#PBS -l nodes=1:r662:ppn=4

# Directories
Project_Folder="/home/a67649/tese/benchmarks/ScholarShip_public/JGF/Sparse/JGF_original"

# Inicializar Java (Não Necessario)

# Inicializar VTune
/share/apps/intel/vtune_amplifier_xe_2017/amplxe-vars.sh

# Ir Para Pasta
cd $Project_Folder

# Compilar
# seq
javac -g -d classes ./seq/*.java

# Correr com VTune
amplxe-cl -collect hotspots -- java -Xcomp -Djava.library.path=native_lib/ia32 -cp ${Project_Folder}/classes/ seq.JGFSparseMatmultBenchSizeA

