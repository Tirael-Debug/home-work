package com.sbrf.reboot.atm.cassettes;

import com.sbrf.reboot.atm.banknotes.Banknote;

import java.util.List;

public class Cassette<T extends Banknote> {

    private final List<T> banknotes;

    public Cassette(List<T> banknotes) {
        this.banknotes = banknotes;
    }

    public int getCountBanknotes() {
        return this.banknotes.size();
    }
}
