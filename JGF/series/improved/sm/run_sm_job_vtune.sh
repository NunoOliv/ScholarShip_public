# PBS -N 
#PBS -l walltime=00:05:00
#PBS -q mei

# PBS -m abe
# PBS -M nuno.oliv.94@gmail.com

#PBS -l nodes=1:r662:ppn=4

# Directories
if [ $JavaV == "j9" ]; then
    Java_dir="/home/a67649/apps/jdk9/bin/"
elif [ $JavaV == "j8" ]; then
    Java_dir="/share/apps/java/jdk1.8.0_20/bin/"
    else exit 1
fi

Project_Folder="/home/a67649/tese/benchmarks/ScholarShip_public/JGF/series/improved"
Vtune_output_dir="/home/a67649/tese/benchmarks/ScholarShip_public/JGF/series/improved/Vtune_out/smSeries_$JavaV"
Classes_dir="classes_$JavaV"


# Inicializar VTune
source /share/apps/intel/vtune_amplifier_xe_2017/amplxe-vars.sh
source /share/apps/intel/vtune_amplifier_xe_2017/sep_vars.sh

# Ir Para Pasta
cd $Project_Folder

# Compilar
mkdir -p $Classes_dir
# seq
${Java_dir}/java -version
${Java_dir}/javac -g -d ./$Classes_dir ./sm/*.java
# Correr com VTune
# Criar pasta
mkdir -p "/home/a67649/tese/benchmarks/ScholarShip_public/JGF/series/improved/Vtune_out/smSeries_$JavaV"
cd $Vtune_output_dir
(>&2 echo "${Project_Folder}/${Classes_dir}/")

if [ $Test == "hs" ]; then
	echo "Collecting HotSpots"
	(>&2 echo "Collecting hotspots")
	amplxe-cl -collect hotspots -- ${Java_dir}/java -Xcomp -Djava.library.path=native_lib/ia32 -cp ${Project_Folder}/${Classes_dir}/ sm_improved.JGFSeriesBenchSizeA -size 5 4
elif [ $Test == "ma" ]; then
	echo "Collecting Memory-access"
	(>&2 echo "Collecting Memory_Acess")
	amplxe-cl -collect memory-access -- ${Java_dir}/java -Xcomp -Djava.library.path=native_lib/ia32 -cp ${Project_Folder}/${Classes_dir}/ sm_improved.JGFSeriesBenchSizeA -size 5 4
elif [ $Test == "ah" ]; then
	echo "Collecting advanced-hotspots"
	(>&2 echo "Collecting advanced-hotspots")
	amplxe-cl -collect advanced-hotspots -- ${Java_dir}/java -Xcomp -Djava.library.path=native_lib/ia32 -cp ${Project_Folder}/${Classes_dir}/ sm_improved.JGFSeriesBenchSizeA -size 5 4
elif [ $Test == "ge" ]; then
	echo "Collecting general-exploration"
	(>&2 echo "Collecting general-exploration")
	amplxe-cl -collect general-exploration -- ${Java_dir}/java -Xcomp -Djava.library.path=native_lib/ia32 -cp ${Project_Folder}/${Classes_dir}/ sm_improved.JGFSeriesBenchSizeA -size 5 4
fi
