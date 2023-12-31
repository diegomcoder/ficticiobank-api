package com.diegomd.ficticiobankapi.database;

import com.diegomd.ficticiobankapi.entities.section.SectionModel;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SECTIONS_DB {
    private static final List<SectionModel> SECTION_MODELS = new ArrayList<>();

    public static SectionModel save(SectionModel sectionModel) {
        SECTION_MODELS.add(sectionModel);
        return sectionModel;
    }

    public static SectionModel findByAtmClientId(UUID atmClientId) {
        SectionModel foundSectionModel = null;
        for (SectionModel sectionModel : SECTION_MODELS) {
            if (sectionModel.getAtmClientId().equals(atmClientId)) {
                foundSectionModel = sectionModel;
                break;
            }
        }
        return foundSectionModel;
    }
}
