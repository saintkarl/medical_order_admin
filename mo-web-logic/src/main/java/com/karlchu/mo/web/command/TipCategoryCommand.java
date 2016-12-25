package com.karlchu.mo.web.command;

import com.karlchu.mo.core.dto.TipCategoryDTO;

/**
 * Created by khanhcq on 25-Oct-16.
 */
public class TipCategoryCommand extends AbstractCommand<TipCategoryDTO> {
    public TipCategoryCommand() {
        this.pojo = new TipCategoryDTO();
    }
}
