package com.diegomd.ficticiobankapi.database;

import com.diegomd.ficticiobankapi.models.Section;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SECTIONS_DB {
    private static final List<Section> sections = new ArrayList<>();

    public static Section save(Section section) {
        sections.add(section);
        return section;
    }

    public static Section findByAtmClientId(UUID atmClientId) {
        Section foundSection = null;
        for (Section section : sections) {
            if (section.getAtmClientId().equals(atmClientId)) {
                foundSection = section;
                break;
            }
        }
        return foundSection;
    }
}
