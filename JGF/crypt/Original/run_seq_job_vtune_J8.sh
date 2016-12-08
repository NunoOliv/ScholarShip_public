# PBS -N 
#PBS -l walltime=00:05:00
#PBS -q mei

# PBS -m abe
# PBS -M nuno.oliv.94@gmail.com

#PBS -l nodes=1:r662:ppn=48

# Directories
Project_Folder="/home/a67649/tese/benchmarks/ScholarShip_public/JGF/crypt/Original"
Vtune_output_dir="/home/a67649/tese/benchmarks/ScholarShip_public/JGF/crypt/Original/Vtune_out/seqJGF_j8"
# Inicializar Java 
Java_dir="/share/apps/java/jdk1.8.0_20/bin/"


# Inicializar VTune
source /share/apps/intel/vtune_amplifier_xe_2017/amplxe-vars.sh
source /share/apps/intel/vtune_amplifier_xe_2017/sep_vars.sh

# Ir Para Pasta
cd $Project_Folder

# Compilar
mkdir -p classes_j8
# seq
${Java_dir}/javac -g -d ./classes_j8 ./seqJGF/*.java
# Correr com VTune
# Criar pasta
mkdir -p "/home/a67649/tese/benchmarks/ScholarShip_public/JGF/crypt/Original/Vtune_out/seqJGF_j8"
cd $Vtune_output_dir

echo "Collecting HotSpots"
(>&2 echo "Collecting hotspots")
amplxe-cl -collect hotspots -- ${Java_dir}/java -Xcomp -Djava.library.path=native_lib/ia32 -cp ${Project_Folder}/classes_j8/ seqJGF.JGFCryptBenchSizeA -size 5
echo "Collecting Memory-access"
(>&2 echo "Collecting Memory_Acess")
amplxe-cl -collect memory-access -- ${Java_dir}/java -Xcomp -Djava.library.path=native_lib/ia32 -cp ${Project_Folder}/classes_j8/ seqJGF.JGFCryptBenchSizeA -size 5
(>&2 echo "Collecting advanced-hotspots")
amplxe-cl -collect advanced-hotspots -- ${Java_dir}/java -Xcomp -Djava.library.path=native_lib/ia32 -cp ${Project_Folder}/classes_j8/ seqJGF.JGFCryptBenchSizeA -size 5
echo "Collecting general-exploration"
(>&2 echo "Collecting general-exploration")
amplxe-cl -collect general-exploration -- ${Java_dir}/java -Xcomp -Djava.library.path=native_lib/ia32 -cp ${Project_Folder}/classes_j8/ seqJGF.JGFCryptBenchSizeA -size 5
