package hw;

import hw.exception.CPUFrequencyException;
import hw.exception.CoresException;
import hw.exception.RAMException;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode
@NoArgsConstructor
public class Computer implements Switchable {
    private static final int MIN_CPU_FREQUENCY = 1;
    private static final int MAX_CPU_FREQUENCY = 10;
    private static final int MIN_CORES = 1;
    private static final int MAX_CORES = 24;
    private static final int MIN_RAM = 2;
    private static final int MAX_RAM = 1024;
    private ComputerBrand name;
    private double CPUFrequency;
    private int cores;
    private int RAM;
    private int memory;

    public Computer(ComputerBrand name) {
        this.name = name;
    }

    public void setCPU_frequency(double CPUFrequency) {
        if (CPUFrequency > 10 || CPUFrequency < 1) {
            throw new CPUFrequencyException("Wrong CPU_frequency. Enter acceptable.");
        }
        this.CPUFrequency = CPUFrequency;
    }

    public void setCores(int cores) {
        if (cores > 24 || cores < 1) {
            throw new CoresException("Wrong cores. Enter acceptable.");
        }
        this.cores = cores;
    }

    public void setRAM(int RAM) {
        if (RAM < 2 || RAM > 1024) {
            throw new RAMException("Wrong RAM. Enter acceptable.");
        }
        this.RAM = RAM;
    }

    @Override
    public String switchOn() {
        return "Switching on";
    }

    @Override
    public String switchOff() {
        return "Switching off";
    }

    @Override
    public String reboot() {
        return "Reboot...";
    }

    @CustomNotation
    public int cleanMemory() {
        int trash = memory;
        memory = 0;
        return trash;
    }
}

