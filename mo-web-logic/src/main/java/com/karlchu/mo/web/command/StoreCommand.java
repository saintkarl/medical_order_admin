package com.karlchu.mo.web.command;

import com.karlchu.mo.core.dto.StoreDTO;

/**
 * Created by khanhcq on 25-Oct-16.
 */
public class StoreCommand extends AbstractCommand<StoreDTO> {
    public StoreCommand() {
        this.pojo = new StoreDTO();
    }
}
