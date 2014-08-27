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
* ajpf: A modified version of AJPF to incorporate organizational properties as used in AORTA
* modelchecker: A model checker for AORTA using AJPF. Enables verification of AIL-systems integrated with AORTA.
* examples: A collection of example programs that can be run in AORTA+Jason

### Integration with Jason
The *jeditplugin* includes a build-configuration that creates a consolidated jar including AORTA and all dependencies. This jar (aorta-standalone.jar) furthermore comes with an installer, which installs AORTA into an existing Jason installation directory.

It can be run with the following command:
```
java -jar aorta-standalone.jar install <Jason-location>
```