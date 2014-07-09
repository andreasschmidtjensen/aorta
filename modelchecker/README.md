# Model Checking AORTA

## Setup
1. Download and unzip AORTA
2. Install JPF-Core
3. Edit site.properties in ~/.jpf to include the following line:
     
        mcapl = <Parent of AORTA project dir>/aorta/ajpf

4. Run aorta.modelchecker.Modelchecker AIL-file PSL-file to model check the system in AIL-file using all the properties in PSL-file
 