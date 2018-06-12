package com.vp.plugin.sample.componentdiagram.actions;

import java.awt.Point;

import com.vp.plugin.ApplicationManager;
import com.vp.plugin.DiagramManager;
import com.vp.plugin.action.VPAction;
import com.vp.plugin.action.VPActionController;
import com.vp.plugin.diagram.IClassDiagramUIModel;
import com.vp.plugin.diagram.IComponentDiagramUIModel;
import com.vp.plugin.diagram.IShapeTypeConstants;
import com.vp.plugin.diagram.connector.IRealizationUIModel;
import com.vp.plugin.diagram.connector.IUsageUIModel;
import com.vp.plugin.diagram.shape.IComponentUIModel;
import com.vp.plugin.diagram.shape.IPortUIModel;
import com.vp.plugin.diagram.shape.IStructuredInterfaceUIModel;
import com.vp.plugin.model.IClass;
import com.vp.plugin.model.IComponent;
import com.vp.plugin.model.IPort;
import com.vp.plugin.model.IRealization;
import com.vp.plugin.model.IStructuredClass;
import com.vp.plugin.model.IUsage;
import com.vp.plugin.model.factory.IModelElementFactory;

public class ComponentDiagramActionControl implements VPActionController {

	@Override
	public void performAction(VPAction arg0) {
		// create blank component diagram
		DiagramManager diagramManager = ApplicationManager.instance().getDiagramManager();
		IComponentDiagramUIModel diagram = (IComponentDiagramUIModel) diagramManager.createDiagram(DiagramManager.DIAGRAM_TYPE_COMPONENT_DIAGRAM);
		
		// create the component model
		IComponent compOrder = IModelElementFactory.instance().createComponent();
		compOrder.setName("Order");
		// create component shape on diagram
		IComponentUIModel compOrderShape = (IComponentUIModel) diagramManager.createDiagramElement(diagram, compOrder);
		// specify its location & dimension on diagram
		compOrderShape.setBounds(358, 211, 115, 57);
		// set to automatic calculate the initial caption position
		compOrderShape.setRequestResetCaption(true); 	
		
		// create the port on Order component
		// this port will used to create realization to OrderEntry interface
		IPort portOrderOrderEntry = compOrder.createPort();
		// create the port shape on diagram
		IPortUIModel portOrderOrderEntryShape = (IPortUIModel) diagramManager.createDiagramElement(diagram, portOrderOrderEntry);
		// specify its location & dimension on diagram
		portOrderOrderEntryShape.setBounds(352, 239, 12, 12);
		// add the port to become the child of the Order component
		compOrderShape.addChild(portOrderOrderEntryShape);
		
		IPort portOrderOrderableItem = compOrder.createPort();
		IPortUIModel portOrderOrderableItemShape = (IPortUIModel) diagramManager.createDiagramElement(diagram, portOrderOrderableItem);
		portOrderOrderableItemShape.setBounds(409, 262, 12, 12);
		compOrderShape.addChild(portOrderOrderableItemShape);
		
				
		IPort portOrderPerson = compOrder.createPort();
		IPortUIModel portOrderPersonShape = (IPortUIModel) diagramManager.createDiagramElement(diagram, portOrderPerson);
		portOrderPersonShape.setBounds(467, 239, 12, 12);
		compOrderShape.addChild(portOrderPersonShape);
			
		
		IComponent compShoppingCart = IModelElementFactory.instance().createComponent();
		compShoppingCart.setName("ShoppingCart");
		IComponentUIModel compShoppingCartShape = (IComponentUIModel) diagramManager.createDiagramElement(diagram, compShoppingCart);
		compShoppingCartShape.setBounds(67, 211, 115, 57);
		compShoppingCartShape.setRequestResetCaption(true);
		
		IPort portShoppingCardOrderEntry = compShoppingCart.createPort();
		IPortUIModel portShoppingCardOrderEntryShape = (IPortUIModel) diagramManager.createDiagramElement(diagram, portShoppingCardOrderEntry);
		portShoppingCardOrderEntryShape.setBounds(178, 238, 12, 12);
		compShoppingCartShape.addChild(portShoppingCardOrderEntryShape);
		
		// create the OrderEntry class
		IClass orderEntry = IModelElementFactory.instance().createClass();
		orderEntry.setName("OrderEntry");
		// assign it with Interface stereotype
		orderEntry.addStereotype("Interface");
		// create structured interface shape
		IStructuredInterfaceUIModel orderEntryShape = (IStructuredInterfaceUIModel) diagramManager.createDiagramElement(diagram, IShapeTypeConstants.SHAPE_TYPE_STRUCTURED_INTERFACE);
		// assign the class as the model element of the structured interface shape
		orderEntryShape.setModelElement(orderEntry);
		// specify its location & dimension on diagram
		orderEntryShape.setBounds(269, 235, 20, 20);
		// set to automatic calculate the initial caption position
		orderEntryShape.setRequestResetCaption(true);
		
		// create realization relationship model
		IRealization realizationOrderOrderEntry = IModelElementFactory.instance().createRealization();
		// specify the from end as the port on the Order component
		realizationOrderOrderEntry.setFrom(portOrderOrderEntry);
		// specify the to end as the OrderEntry interface class
		realizationOrderOrderEntry.setTo(orderEntry);
		// create the connector shape on diagram,
		// with points as null since it is a straight connector
		diagramManager.createConnector(diagram, realizationOrderOrderEntry, orderEntryShape, portOrderOrderEntryShape, null);
		
		// create usage relationship model
		IUsage usageShoppingCartOrderEntry = IModelElementFactory.instance().createUsage();
		// specify the from end as port on the ShoppingCart
		usageShoppingCartOrderEntry.setFrom(portShoppingCardOrderEntry);
		// specify the to end as the OrderEntery
		usageShoppingCartOrderEntry.setTo(orderEntry);
		// create the connector shape on diagram
		diagramManager.createConnector(diagram,  usageShoppingCartOrderEntry, portShoppingCardOrderEntryShape, orderEntryShape, null);
		
		
		IComponent compBackOrder = IModelElementFactory.instance().createComponent();
		compBackOrder.setName("BackOrder");
		IComponentUIModel compBackOrderShape = (IComponentUIModel) diagramManager.createDiagramElement(diagram, compBackOrder);
		compBackOrderShape.setBounds(358, 43, 115, 57);
		compBackOrderShape.setRequestResetCaption(true);		
		
		IPort portBackOrderPerson = compBackOrder.createPort();
		IPortUIModel portBackOrderPersonShape = (IPortUIModel) diagramManager.createDiagramElement(diagram, portBackOrderPerson);
		portBackOrderPersonShape.setBounds(467, 70, 12, 12);
		compBackOrderShape.addChild(portBackOrderPersonShape);

		
		IComponent compProduct = IModelElementFactory.instance().createComponent();
		compProduct.setName("Product");
		IComponentUIModel compProductShape = (IComponentUIModel) diagramManager.createDiagramElement(diagram, compProduct);
		compProductShape.setBounds(358, 403, 115, 57);
		compProductShape.setRequestResetCaption(true);		

		IPort portProductOrderableItem = compProduct.createPort();
		IPortUIModel portProductOrderableItemShape = (IPortUIModel) diagramManager.createDiagramElement(diagram, portProductOrderableItem);
		portProductOrderableItemShape.setBounds(409, 397, 12, 12);
		compProductShape.addChild(portProductOrderableItemShape);
		
		IClass orderableItem = IModelElementFactory.instance().createClass();
		orderableItem.setName("OrderableItem");
		orderableItem.addStereotype("Interface");
		
		IStructuredInterfaceUIModel orderableItemShape = (IStructuredInterfaceUIModel) diagramManager.createDiagramElement(diagram, IShapeTypeConstants.SHAPE_TYPE_STRUCTURED_INTERFACE);
		orderableItemShape.setModelElement(orderableItem);
		orderableItemShape.setBounds(405, 307, 20, 20);
		orderableItemShape.setRequestResetCaption(true);
		
		IRealization realizationProductOrderableItem = IModelElementFactory.instance().createRealization();
		realizationProductOrderableItem.setFrom(portProductOrderableItem);
		realizationProductOrderableItem.setTo(orderableItem);
		diagramManager.createConnector(diagram, realizationProductOrderableItem, orderableItemShape, portProductOrderableItemShape, null);
		
		IUsage usageOrderOrderableItem = IModelElementFactory.instance().createUsage();
		usageOrderOrderableItem.setFrom(portOrderOrderableItem);
		usageOrderOrderableItem.setTo(orderableItem);
		diagramManager.createConnector(diagram,  usageOrderOrderableItem, portOrderOrderableItemShape, orderableItemShape, null);
		
		
		IComponent compCustomer = IModelElementFactory.instance().createComponent();
		compCustomer.setName("Customer");
		IComponentUIModel compCustomerShape = (IComponentUIModel) diagramManager.createDiagramElement(diagram, compCustomer);
		compCustomerShape.setBounds(711, 211, 115, 57);
		compCustomerShape.setRequestResetCaption(true);		
		
		IPort portCustomerPerson = compCustomer.createPort();
		IPortUIModel portCustomerPersonShape = (IPortUIModel) diagramManager.createDiagramElement(diagram, portCustomerPerson);
		portCustomerPersonShape.setBounds(705, 239, 12, 12);
		compCustomerShape.addChild(portCustomerPersonShape);

		IClass person = IModelElementFactory.instance().createClass();
		person.setName("Person");
		person.addStereotype("Interface");
		
		IStructuredInterfaceUIModel personShape = (IStructuredInterfaceUIModel) diagramManager.createDiagramElement(diagram, IShapeTypeConstants.SHAPE_TYPE_STRUCTURED_INTERFACE);
		personShape.setModelElement(person);
		personShape.setBounds(582, 235, 20, 20);
		personShape.setRequestResetCaption(true);
		
		IRealization realizationCustomerPerson = IModelElementFactory.instance().createRealization();
		realizationCustomerPerson.setFrom(portCustomerPerson);
		realizationCustomerPerson.setTo(person);
		diagramManager.createConnector(diagram, realizationCustomerPerson, personShape, portCustomerPersonShape, null);
		
		IUsage usageOrderPerson = IModelElementFactory.instance().createUsage();
		usageOrderPerson.setFrom(portOrderPerson);
		usageOrderPerson.setTo(person);
		diagramManager.createConnector(diagram,  usageOrderPerson, portOrderPersonShape, personShape, null);
		
		
		IComponent compOrganization = IModelElementFactory.instance().createComponent();
		compOrganization.setName("Organization");
		IComponentUIModel compOrganizationShape = (IComponentUIModel) diagramManager.createDiagramElement(diagram, compOrganization);
		compOrganizationShape.setBounds(711, 337, 115, 57);
		compOrganizationShape.setRequestResetCaption(true);		

		IPort portOrganization = compOrganization.createPort();
		IPortUIModel portOrganizationShape = (IPortUIModel) diagramManager.createDiagramElement(diagram, portOrganization);
		portOrganizationShape.setBounds(705, 365, 12, 12);
		compOrganizationShape.addChild(portOrganizationShape);

		IRealization realizationOrganizationPerson = IModelElementFactory.instance().createRealization();
		realizationOrganizationPerson.setFrom(portOrganization);
		realizationOrganizationPerson.setTo(person);
		diagramManager.createConnector(diagram, realizationOrganizationPerson, personShape, portOrganizationShape, new Point[] {new Point(602, 245), new Point(650, 245), new Point(650, 372), new Point(705, 372)});

		IUsage usageBackOrderPerson = IModelElementFactory.instance().createUsage();
		usageBackOrderPerson.setFrom(portBackOrderPerson);
		usageBackOrderPerson.setTo(person);
		diagramManager.createConnector(diagram, usageBackOrderPerson, portBackOrderPersonShape, personShape, new Point[] {new Point(479, 76), new Point(540, 76), new Point(540, 245), new Point(582, 245)});
		
		
		// show up the diagram		
		diagramManager.openDiagram(diagram);

	}

	@Override
	public void update(VPAction arg0) {
		// TODO Auto-generated method stub
		
	}

}
