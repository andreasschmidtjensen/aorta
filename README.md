## AORTA: Adding Organizational Reasoning to Agents

AORTA is an organizational reasoning component that can be integrated into the agent's reasoning mechanism, allowing it to reason about (and act upon) regulations specified by an organizational model using simple reasoning rules.
Read more about AORTA [here](http://www2.compute.dtu.dk/~ascje/AORTA/).

The project consists of the following:

* metamodel: The AORTA metamodel
* opera: A translator from the OperA model to the AORTA metamodel
* framework: The AORTA framework
* jason: The integration with Jason
* jeditplugin: A plugin for JEdit for Jason
* bw4t: An example usage of the jason-integration using Blocks World for Teams
* ajpf2014: A modified version of AJPF to incorporate organizational properties as used in AORTA
* modelchecker: A model checker for AORTA using AJPF. Enables verification of AIL-systems integrated with AORTA.
* examples: A collection of example programs that can be run in AORTA+Jason

AORTA should be built using at least Java 7 (and exactly Java 7 to use the model checker).

### Integration with Jason
The *jeditplugin* includes a build-configuration that creates a  jar including AORTA and all dependencies. This jar (aorta-standalone.jar) furthermore comes with an installer, which installs AORTA into an existing Jason installation directory.

The installer is created using `ant make-installer` in the jeditplugin folder and creates a new folder `installer` containing the jar. 

It can be run with the following command:

    java -jar aorta-standalone.jar install <Jason-location>

### Model Checking
The *modelchecker* works as a JPF plugin. It furthermore includes a build-configuration that creates a jar with all dependencies. This jar (in the folder `standalone`) can be run as follows:

    java -jar aorta-modelchecker.jar AIL-file [PSL-file]

If no PSL-file is provided, the system is executed normally. Otherwise, modelchecking using JPF is performed. The jar should be executed in the working directory of the system to be verified.

#### Setup of the modelchecking system
The system relies on JPF and should therefore be setup as follows:

1. Download and unzip AORTA
2. Install JPF-Core (download and build from JPF repository)
3. Edit site.properties in ~/.jpf to include the following line:
     
        mcapl = <Parent of AORTA project dir>/aorta/ajpf2014

4. Run `aorta.modelchecker.Modelchecker AIL-file PSL-file` (or use the JAR file) to verify the system in AIL-file using all the properties in PSL-file
 