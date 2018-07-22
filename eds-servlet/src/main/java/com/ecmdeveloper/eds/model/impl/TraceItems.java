/**
 * 
 */
package com.ecmdeveloper.eds.model.impl;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * This class manages the trace items.
 * 
 * @author Ricardo Belfor
 *
 */
public class TraceItems implements Iterable<TraceItem> {

	private static final TraceItem emptyItem = new TraceItem("{}", "{}", "");

	private final LinkedList<TraceItem> traceList = new LinkedList<TraceItem>();
	private final int size;
	
	public TraceItems(int size) {
		this.size = size;
	}
	
	@Override
	public Iterator<TraceItem> iterator() {
		return traceList.iterator();
	}

	public void add(TraceItem traceItem) {
		traceList.addFirst(traceItem);
		if ( traceList.size() > size ) {
			traceList.removeLast();
		}		
	}

	public TraceItem get(int index) {
		if ( index < traceList.size() ) {
			return traceList.get(index);
		}
		return emptyItem;
	}
}
